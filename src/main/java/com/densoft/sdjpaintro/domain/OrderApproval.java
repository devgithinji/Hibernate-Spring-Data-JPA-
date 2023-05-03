package com.densoft.sdjpaintro.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

import java.util.Objects;

@Entity
public class OrderApproval extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "order_header_id")
    private OrderHeader orderHeader;
    private String approvedBy;

    public String getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(String approvedBy) {
        this.approvedBy = approvedBy;
    }

    public OrderHeader getOrderHeader() {
        return orderHeader;
    }

    public void setOrderHeader(OrderHeader orderHeader) {
        this.orderHeader = orderHeader;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        OrderApproval that = (OrderApproval) o;

        if (!Objects.equals(orderHeader, that.orderHeader)) return false;
        return Objects.equals(approvedBy, that.approvedBy);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderHeader != null ? orderHeader.hashCode() : 0);
        result = 31 * result + (approvedBy != null ? approvedBy.hashCode() : 0);
        return result;
    }
}
