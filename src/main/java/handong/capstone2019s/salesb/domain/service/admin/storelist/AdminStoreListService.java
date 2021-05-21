package handong.capstone2019s.salesb.domain.service.admin.storelist;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import handong.capstone2019s.salesb.app.admin.storelist.AdminStoreListForm;
import handong.capstone2019s.salesb.domain.model.Seller;
import handong.capstone2019s.salesb.domain.model.Store;

public interface AdminStoreListService {

	Page<Store> showStoreList(AdminStoreListForm store, String adminName, Pageable pageable);
	
	void updateStoreExpiredTrue (String storeCode);
	
	void updateStoreExpiredFalse (String storeCode);
}
