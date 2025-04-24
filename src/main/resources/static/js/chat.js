let stompClient = null;
let currentRoomId = null;
let subscriptions = {}; // 각 방(roomId)별 구독 저장

// 웹소켓 연결 및 초기 구독
// 웹소켓 연결 및 초기 구독
function connect() {
    const roomInput = document.getElementById('roomId');
    const newRoomId = roomInput.value.trim() || "default"; // 기본값은 'default'

    if (!newRoomId) {
        alert("방 ID를 입력해주세요.");
        return;
    }

    // 연결 안 되어 있을 경우 새로 연결
    if (!stompClient || !stompClient.connected) {
        const socket = new SockJS('/ws-chat');
        stompClient = Stomp.over(socket);

        stompClient.connect({}, function (frame) {
            console.log('✅ Connected: ' + frame);

            // 방 목록 구독 (방 목록은 한 번만 구독)
            subscribeRoomList();

            // 새로운 방 구독
            joinRoom(newRoomId);

            // 방 목록 요청
            stompClient.send("/app/chat.getRooms");
        }, function (error) {
            console.error("WebSocket 연결 실패:", error);
            alert("채팅 서버와의 연결에 실패했습니다.");
        });
    } else {
        // 이미 연결된 경우 기존 방 구독 해제 후 새 방 구독
        joinRoom(newRoomId);
    }
}

function joinRoom(roomId) {
    // 기존 방 구독 해제
    if (currentRoomId && subscriptions[currentRoomId]) {
        subscriptions[currentRoomId].unsubscribe();
    }

    currentRoomId = roomId;

    // 새로운 방 구독
    subscriptions[roomId] = stompClient.subscribe("/topic/chat/" + roomId, function (messageOutput) {
        showMessage(JSON.parse(messageOutput.body));
    });

    console.log(`✅ Subscribed to room: ${roomId}`);
    
    

    // 기존 메시지 불러오기 -> 완료 후 입장 메시지 출력
    loadChatHistory(roomId, function () {
        $('#chatBox').append('<div>입장되었습니다: ' + roomId + '</div>');
    });
}




// 메시지 전송
function sendMessage() {
    if (!stompClient || !stompClient.connected) {
        alert("채팅방에 먼저 입장해주세요.");
        return;
    }

    const messageInput = document.getElementById('message');
    const messageContent = messageInput.value.trim();

    if (messageContent === "") return;

    const message = {
        roomId: currentRoomId,
        content: messageContent
        // sender는 서버에서 자동 설정
    };

    stompClient.send("/pub/chat", {}, JSON.stringify(message));
    messageInput.value = '';
}

// 수신된 메시지를 화면에 출력
function showMessage(message) {
    const chatBox = document.getElementById('chatBox');
    const div = document.createElement('div');
    div.textContent = `[${message.sender || '익명'}] ${message.content}`;
    chatBox.appendChild(div);
    chatBox.scrollTop = chatBox.scrollHeight; // 최신 메시지로 스크롤
}

// 방 목록 구독
function subscribeRoomList() {
    stompClient.subscribe('/topic/roomList', function (response) {
        const rooms = JSON.parse(response.body);
        console.log("Received rooms: ", rooms);  // 방 목록 로그 추가
        showRoomList(rooms);  // 방 목록 UI에 표시
    });
}



// 방 목록 UI 표시
function showRoomList(rooms) {
    const roomList = document.getElementById('roomList');
    roomList.innerHTML = ''; // 이전 내용 초기화

    rooms.forEach(room => {
        const roomItem = document.createElement('div');
        roomItem.className = 'room-list-item';
        roomItem.textContent = room;  // 방 ID를 텍스트로 표시
        roomItem.onclick = () => {
            document.getElementById('roomId').value = room;
            connect();  // 방 변경 시 다시 연결/구독
        };
        roomList.appendChild(roomItem);  // roomItem을 방 목록에 추가
    });
}
function loadChatHistory(roomId, callback) {
    fetch(`/api/chat/history/${roomId}`)
        .then(response => response.json())
        .then(messages => {
            const chatBox = document.getElementById('chatBox');
            chatBox.innerHTML = ''; // 기존 메시지 초기화

            messages.forEach(message => {
                showMessage(message); // 기존에 있는 showMessage 사용
            });

            if (callback) callback(); // 메시지 로딩 완료 후 콜백 실행
        })
        .catch(error => {
            console.error('❌ 채팅 히스토리 로드 실패:', error);
            if (callback) callback(); // 실패해도 콜백 실행
        });
}


// 페이지 로드시 자동 연결
//window.addEventListener('load', () => {
//    connect();
//});
