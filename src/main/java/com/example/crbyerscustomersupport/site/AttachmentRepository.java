package com.example.crbyerscustomersupport.site;

import com.example.crbyerscustomersupport.entities.Attachment;

public interface AttachmentRepository extends GenericRespository<Long, Attachment> {
    Attachment getByTicketId(Long ticketId);
}
