package com.ruoyi.dylan.task;

import com.ruoyi.dylan.domain.DylanLiuli;
import com.ruoyi.dylan.service.IDylanLiuliService;
import com.ruoyi.dylan.service.impl.DylanLiuliServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName LiuliTask
 * @Description TODO
 * @Author Dylan
 * @Date 2024/5/2 20:13
 * @Version 1.0
 */
@Component("liuliTask")
public class LiuliTask {

    @Autowired
    private IDylanLiuliService dylanLiuliService;

    public void syncLiuliContent(){
        dylanLiuliService.syncLiuliContent();
    }
}
