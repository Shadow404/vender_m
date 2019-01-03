package com.wemjingwang.demo1.service.serviceImpl;

import com.wemjingwang.demo1.repository.UrlRepository;
import com.wemjingwang.demo1.service.UrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UrlServiceImpl implements UrlService {
    @Autowired
    private UrlRepository urlRepository;
}
