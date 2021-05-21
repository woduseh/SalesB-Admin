package handong.capstone2019s.salesb.domain.service.admin.request;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import handong.capstone2019s.salesb.app.admin.request.AdminRequestForm;
import handong.capstone2019s.salesb.domain.model.Request;

public interface AdminRequestService {

	Page<Request> showRequest(AdminRequestForm request, String adminName, Pageable pageable);
	
	Page<Request> showLimitedRequest(AdminRequestForm request, String adminName, Pageable pageable);
	
	Page<Request> showRequestDetail(int seq, Pageable pageable);
}
