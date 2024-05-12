package com.ruoyi.dylan.task;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.ruoyi.common.core.redis.RedisCache;
import com.ruoyi.dylan.domain.DylanLiuli;
import com.ruoyi.dylan.domain.DylanLiuliLink;
import com.ruoyi.dylan.service.IDylanLiuliLinkService;
import com.ruoyi.dylan.service.IDylanLiuliService;
import com.ruoyi.dylan.service.impl.DylanLiuliServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    @Autowired
    private IDylanLiuliLinkService dylanLiuliLinkService;

    @Autowired
    private RedisCache redisCache;

    /**
     * 定时更新每日琉璃内容
     */
    public void syncLiuliContent(){
        // 查询所有可用的琉璃链接
        List<DylanLiuliLink> liuliLinkList = dylanLiuliLinkService.list(new QueryWrapper<DylanLiuliLink>().lambda()
                .eq(DylanLiuliLink::getChkUse, 1));
        if (ObjectUtils.isNotEmpty(liuliLinkList)){
            for (DylanLiuliLink dylanLiuliLink : liuliLinkList) {
                String mainLink = dylanLiuliLink.getMainLink();
                dylanLiuliService.syncLiuliContent(mainLink);
            }
        }
    }

    /**
     * 定时爬取历年数据
     */

}
