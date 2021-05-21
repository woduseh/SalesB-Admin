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

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import handong.capstone2019s.salesb.app.manageadmin.AdminForm;
import handong.capstone2019s.salesb.app.manageadmin.AdminManageForm;
import handong.capstone2019s.salesb.domain.model.Admin;

public interface AdminManagementService {

    Admin register(Admin admin, String rawPassword);
    
    int nameOverlapChk(Admin admin);
    
	Page<Admin> selectAdmin(AdminManageForm admin, Pageable pageable);
	
	Page<Admin> selectAdminbyAdminCode(int seq, Pageable pageable);
}
