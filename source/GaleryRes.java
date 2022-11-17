package egovframework.com.api.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import egovframework.com.api.aop.Property;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class GaleryRes {

	@Property(name = "work_id")
	private String id;
	@Property(name = "file_id")
	private String fileId;
	@Property(name = "author_nms")
	private String authorName;
	@Property(name = "author_nms_en")
	private String authorNameEn;
	@Property(name = "work_nm")
	private String workNm;
	@Property(name = "make_year")
	private String makeYear;
	@Property(name = "size_width")
	private String sizeWidth;
	@Property(name = "size_vertical")
	private String sizeVertical;
	@Property(name = "size_height")
	private String sizeHeight;
	@Property(name = "matrial_nm")
	private String matrialNm;
	@Property(name = "matrial_nm_en")
	private String matrialNmEn;
	private String price1;
	private String price2;
}
