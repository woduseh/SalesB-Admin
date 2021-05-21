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
package handong.capstone2019s.salesb.domain.service.admin.request;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import handong.capstone2019s.salesb.app.admin.request.AdminRequestForm;
import handong.capstone2019s.salesb.domain.model.Request;
import handong.capstone2019s.salesb.domain.repository.admin.AdminRepository;

@Transactional
@Service
public class AdminRequestServiceImpl implements AdminRequestService {
	@Inject
	AdminRepository adminRepository;

	@Inject
	PasswordEncoder passwordEncoder;

	@Override
	public Page<Request> showRequest(AdminRequestForm request, String adminName, Pageable pageable) {

		long total = adminRepository.countRequestbyAdminName(adminName, request);
		List<Request> content;

		if (0 < total) {
			content = adminRepository.selectRequestbyAdminName(adminName, request, pageable);
		} else {
			content = Collections.emptyList();
		}

		Page<Request> page = new PageImpl<Request>(content, pageable, total);
		return page;
	}
	
	@Override
	public Page<Request> showLimitedRequest(AdminRequestForm request, String adminName, Pageable pageable) {

		long total = adminRepository.countRequestbyAdminName(adminName, request);
		List<Request> content;

		if (0 < total) {
			content = adminRepository.selectLimitedRequestbyAdminName(adminName, request, pageable);
		} else {
			content = Collections.emptyList();
		}

		Page<Request> page = new PageImpl<Request>(content, pageable, total);
		return page;
	}

	@Override
	public Page<Request> showRequestDetail(int seq, Pageable pageable){
		
		List<Request> content = adminRepository.selectRequestbySeq(seq, pageable);
		
		Page<Request> page = new PageImpl<Request>(content, pageable, 1);
		return page;
	}
}
