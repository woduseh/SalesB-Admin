/*
 * Copyright(c) 2013 NTT DATA Corporation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package handong.capstone2019s.salesb.domain.service.admin;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import handong.capstone2019s.salesb.app.manageadmin.AdminForm;
import handong.capstone2019s.salesb.app.manageadmin.AdminManageForm;
import handong.capstone2019s.salesb.domain.model.Admin;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;

@Transactional
@Service
public class AdminManagementServiceImpl implements AdminManagementService {
    @Inject
    AdminRepository adminRepository;


    @Inject
    PasswordEncoder passwordEncoder;

    @Override
    public Admin register(Admin admin, String rawPassword) {

        String password = passwordEncoder.encode(rawPassword);

        admin.setAdminPass(password);
        adminRepository.insertadmin(admin);
        return admin;
    }
    
    @Override
    public int nameOverlapChk(Admin admin) {
    	int result = adminRepository.nameOverlapChk(admin.getAdminName());
    	
    	return result;
    }
    
	@Override
	public Page<Admin> selectAdmin(AdminManageForm admin, Pageable pageable) {

		long total = adminRepository.countAdmin(admin);
		List<Admin> content;

		content = adminRepository.selectAdmin(admin, pageable);

		Page<Admin> page = new PageImpl<Admin>(content, pageable, total);
		return page;
	}

	@Override
	public Page<Admin> selectAdminbyAdminCode(int seq, Pageable pageable){
		
		List<Admin> content = adminRepository.selectAdminbyAdminCode(seq, pageable);
		
		Page<Admin> page = new PageImpl<Admin>(content, pageable, 1);
		return page;
	}
}
