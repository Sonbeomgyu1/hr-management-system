package com.apple.shop.project;

import com.apple.shop.CustomerMember.CustomerMember;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_member")
@Getter
@Setter
@NoArgsConstructor
public class ProjectMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "customer_member_id", nullable = true)
    private CustomerMember customerMember;

    // customerMemberId는 실제 DB에는 저장되지 않고,
    // customerMember 객체를 통해 참조되는 값만 사용하도록 할 수 있습니다.
    @Column(name = "customer_member_id", insertable = false, updatable = false)
    private Long customerMemberId;

    private String role;
    private Integer allocationRate;

    // Transient를 사용하여 customerMemberId 값을 계산하여 반환합니다.
    @Transient
    public Long getCustomerMemberId() {
        if (customerMember != null) {
            return customerMember.getId();
        }
        return this.customerMemberId;
    }
}
