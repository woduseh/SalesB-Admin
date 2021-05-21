package handong.capstone2019s.salesb.domain.service.admin.storelist;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import handong.capstone2019s.salesb.app.admin.storelist.AdminStoreListForm;
import handong.capstone2019s.salesb.domain.model.Admin;
import handong.capstone2019s.salesb.domain.model.Seller;
import handong.capstone2019s.salesb.domain.model.Store;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;

@Transactional
@Service
public class AdminStoreListServiceImpl implements AdminStoreListService {
	@Inject
	AdminRepository adminRepository;

	@Inject
	PasswordEncoder passwordEncoder;

	@Override
	public Page<Store> showStoreList(AdminStoreListForm store, String adminName, Pageable pageable) {

		long total = adminRepository.countStoreListbyAdminName(adminName, store);
		List<Store> content;

		if (0 < total) {
			content = adminRepository.selectStoreListbyAdminName(adminName, store, pageable);
		} else {
			content = Collections.emptyList();
		}

		Page<Store> page = new PageImpl<Store>(content, pageable, total);
		return page;
	}

	@Override
	public void updateStoreExpiredTrue(String storeCode) {
		adminRepository.updateStoreExpiredTrue (storeCode);
	}

	@Override
	public void updateStoreExpiredFalse(String storeCode) {
		adminRepository.updateStoreExpiredFalse (storeCode);
	}
}
