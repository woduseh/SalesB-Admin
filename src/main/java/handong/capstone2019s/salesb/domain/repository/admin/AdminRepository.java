package handong.capstone2019s.salesb.domain.repository.admin;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

import handong.capstone2019s.salesb.app.admin.request.AdminRequestForm;
import handong.capstone2019s.salesb.app.admin.storelist.AdminStoreListForm;
import handong.capstone2019s.salesb.app.manageadmin.AdminForm;
import handong.capstone2019s.salesb.app.manageadmin.AdminManageForm;
import handong.capstone2019s.salesb.domain.model.Admin;
import handong.capstone2019s.salesb.domain.model.Request;
import handong.capstone2019s.salesb.domain.model.Store;

public interface AdminRepository {

	/* Admin Management Service */
	
    Optional<Admin> findByAdminId(String adminCode);
    
    Optional<Admin> findByAdminName(String adminName);
    
    int nameOverlapChk(String adminName);
    
    void insertadmin(Admin admin);
    
    List<Admin> selectAdmin(@Param("adminform") AdminManageForm adminform, Pageable pageable);
    
    List<Admin> selectAdminbyAdminCode(@Param("admincode") int admincode, Pageable pageable);
    
    long countAdmin (@Param("adminform") AdminManageForm adminform);
    
    /* Admin Request Service */
    
    // @param 을 사용해서 구체적으로 어떤 변수가 어떤 파라미터와 연결되는지 지정
    List<Request> selectRequestbyAdminName(@Param("thePersonInCharge") String adminName, @Param("requestform") AdminRequestForm request, Pageable pageable);

    List<Request> selectRequestbySeq(@Param("seq") int seq, Pageable pageable);
    
    long countRequestbyAdminName (@Param("thePersonInCharge") String adminName, @Param("requestform") AdminRequestForm request);
    
    List<Request> selectLimitedRequestbyAdminName(@Param("thePersonInCharge") String adminName, @Param("requestform") AdminRequestForm request, Pageable pageable);
    
    /* Admin StoreList Service */
    List<Store> selectStoreListbyAdminName(@Param("sellerManageAdmin") String adminName, @Param("storeform") AdminStoreListForm store, Pageable pageable);

    long countStoreListbyAdminName (@Param("sellerManageAdmin") String adminName, @Param("storeform") AdminStoreListForm store);
    
    void updateStoreExpiredTrue (@Param("storeCode") String storeCode);
    
    void updateStoreExpiredFalse (@Param("storeCode") String storeCode);
}
