package project;

import java.util.List;

public class Customer {
    private String name;
    private int memberId;
    private boolean membershipActive;
    private List<Vehicle> Rentable;

    public Customer(String name, int memberId, boolean membershipActive) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Try again you forgot to fill in your name.");
        }
        this.name = name;
        this.memberId = memberId;
        this.membershipActive = membershipActive;
        this.Rentable = null;
    }

    public void payOrder(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("You have an empty order try again.");
        }
        double discount = membershipActive ? 0.2 : 0.0; 
        double amount = order.getTotalAmount() * (1 - discount);
        System.out.printf("%s paid $%.2f for Order #%d%n", name, amount, order.getOrderId());
    }
