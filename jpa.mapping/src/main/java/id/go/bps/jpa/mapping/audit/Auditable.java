package id.go.bps.jpa.mapping.audit;

import java.util.Date;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter(value = AccessLevel.PROTECTED)
@Setter(value = AccessLevel.PROTECTED)
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Auditable<U> {
	
	@CreatedBy
	protected U createdBy;
	
	@CreatedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date createdDate;
	
	@LastModifiedBy
	protected U modifiedBy;
	
	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	protected Date modifiedDate;
}
