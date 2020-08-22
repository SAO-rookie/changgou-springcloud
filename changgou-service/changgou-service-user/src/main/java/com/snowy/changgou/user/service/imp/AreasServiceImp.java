package com.snowy.changgou.user.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snowy.changgou.user.entity.Areas;
import com.snowy.changgou.user.mapper.AreasMapper;
import com.snowy.changgou.user.service.AreasService;
import org.springframework.stereotype.Service;

/**
 * @author snowy
 * @date 2020/8/21 21:57
 */
@Service
public class AreasServiceImp extends ServiceImpl<AreasMapper, Areas> implements AreasService {
}
