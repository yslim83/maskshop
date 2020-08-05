package maskShop3;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import java.util.List;

@Entity
@Table(name="Inventory_table")
public class Inventory {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private Long productId;
    private Integer invQty;

// 0805 lys 수정 start
    @PostPersist
    public void onPrePersist(){

        InvRegisterd invRegisterd = new InvRegisterd();

        System.out.println("##### INVENTORY onPrePersist=======================" +invRegisterd.toJson());

        BeanUtils.copyProperties(this, invRegisterd);
        invRegisterd.publishAfterCommit();

    }
    // 0805 lys 수정 end


    @PostUpdate
    public void onPostUpdate(){

        System.out.println("onpost updatd");
        InvUpdated invUpdated = new InvUpdated();
        BeanUtils.copyProperties(this, invUpdated);
        invUpdated.publishAfterCommit();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }
    public Integer getInvQty() {
        return invQty;
    }

    public void setInvQty(Integer invQty) {
        this.invQty = invQty;
    }
}
