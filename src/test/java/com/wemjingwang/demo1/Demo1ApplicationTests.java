package com.wemjingwang.demo1;

import com.wemjingwang.demo1.domain.VenderOrder;
import com.wemjingwang.demo1.domain.VenderStock;
import com.wemjingwang.demo1.repository.VenderOrderRepository;
import com.wemjingwang.demo1.repository.VenderRepository;
import com.wemjingwang.demo1.repository.VenderStockRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class Demo1ApplicationTests {
    @Autowired
    private VenderOrderRepository venderRepository;
    @Autowired
    private VenderStockRepository venderStockRepository;
    @Test
    public void contextLoads() {
        VenderOrder venderOrder = new VenderOrder();
        venderOrder.setGoodName("王静文1");
        venderOrder.setGoodPrice(BigDecimal.valueOf(5));
        venderOrder.setGoodCount(BigDecimal.valueOf(6));
        venderOrder.setSellerId(6);
        venderOrder.setVenderId(15);
        List<VenderStock> stocks=venderStockRepository.findAllByVenderId(venderOrder.getVenderId(),venderOrder.getGoodName());
        BigDecimal venderOrderCount=venderOrder.getGoodCount();
        for (VenderStock stock:stocks) {
            BigDecimal goodCount=stock.getVenderGoodCount();
            if(venderOrderCount.compareTo(BigDecimal.valueOf(0))==0){
                log.info("购买成功！");
                break;
            }

            if(goodCount.subtract(venderOrderCount).compareTo(BigDecimal.valueOf(0))>=0){

                int result1=venderStockRepository.delVenderStockByBuyer(venderOrderCount,stock.getVenderStockId());
                if(result1>0){
                    log.info("购买成功！");
                    break;
                }
            }
            else if(venderOrderCount.compareTo(goodCount)>=0){
                int reusult=venderStockRepository.delVenderStockByBuyer(goodCount,stock.getVenderStockId());
                if(reusult>0){
                    venderOrderCount=venderOrderCount.subtract(goodCount);
                }
            }
        }
        venderRepository.save(venderOrder);
    }

}

