package com.example.fruit_api.service;

import com.example.fruit_api.dto.OrderDto;
import com.example.fruit_api.common.api.exception.ResourceNotFoundException;
import com.example.fruit_api.models.Order;
import com.example.fruit_api.models.OrderDetail;
import com.example.fruit_api.models.Product;
import com.example.fruit_api.repository.OrderDetailRepository;
import com.example.fruit_api.repository.OrderRepository;
import com.example.fruit_api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        Order order = new Order();

        // Set customer (for now, assume a default customer)
        // In a real application, you'd fetch the customer based on user authentication
        // or another mechanism.
        // For now, we are not handling Customer in this example to simplify
        // You can modify this later to include actual customer data.

        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Order.OrderStatus.PENDING);
        // Calculate the total amount and create order details
        BigDecimal totalAmount = BigDecimal.ZERO;
        List<OrderDetail> orderDetails = orderDto.getOrderDetails().stream().map(detailDto -> {
            Product product = productRepository.findById(detailDto.getProductId())
                    .orElseThrow(() -> new ResourceNotFoundException("Product not found with id: " + detailDto.getProductId()));

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setQuantity(detailDto.getQuantity());
            orderDetail.setUnitPrice(product.getPrice());

            // Calculate the total amount for the order
            BigDecimal lineTotal = product.getPrice().multiply(BigDecimal.valueOf(detailDto.getQuantity()));

            return orderDetail;
        }).collect(Collectors.toList());

        for (OrderDetail detail : orderDetails) {
            totalAmount = totalAmount.add(detail.getUnitPrice().multiply(BigDecimal.valueOf(detail.getQuantity())));
        }

        order.setTotalAmount(totalAmount);
        order.setOrderDetails(orderDetails);

        // Save the order (this will also save the order details due to cascade)
        Order savedOrder = orderRepository.save(order);

        return convertToDto(savedOrder);
    }

    public List<OrderDto> getAllOrders() {
        return orderRepository.findAllByOrderByOrderDateDesc().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long orderId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));
        return convertToDto(order);
    }

    @Transactional
    public OrderDto updateOrderStatus(Long orderId, Order.OrderStatus newStatus) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + orderId));

        order.setStatus(newStatus);
        Order updatedOrder = orderRepository.save(order);
        return convertToDto(updatedOrder);
    }

    private OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderDate(order.getOrderDate());
        orderDto.setTotalAmount(order.getTotalAmount());
        orderDto.setStatus(order.getStatus());
        List<OrderDto.OrderDetailDto> orderDetailDtos = order.getOrderDetails().stream().map(orderDetail -> {
            OrderDto.OrderDetailDto detailDto = new OrderDto.OrderDetailDto();
            detailDto.setOrderDetailId(orderDetail.getOrderDetailId());
            detailDto.setProductId(orderDetail.getProduct().getProductId());
            detailDto.setProductName(orderDetail.getProduct().getProductName());
            detailDto.setQuantity(orderDetail.getQuantity());
            detailDto.setUnitPrice(orderDetail.getUnitPrice());
            return detailDto;
        }).collect(Collectors.toList());

        orderDto.setOrderDetails(orderDetailDtos);
        return orderDto;
    }
}