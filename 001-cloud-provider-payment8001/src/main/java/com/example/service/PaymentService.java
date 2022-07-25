package com.example.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dao.PaymentDao;
import com.example.entities.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaymentService extends ServiceImpl<PaymentDao, Payment> implements IService<Payment> {
    public int create(Payment payment) {
        return baseMapper.create(payment);
    }
}
