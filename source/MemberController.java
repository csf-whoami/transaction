package egovframework.com.api.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import egovframework.com.api.constants.UrlConstant;
import egovframework.com.api.response.MainListRes;
import egovframework.com.api.service.ArtfairService;
import egovframework.com.api.service.AuctionService;
import egovframework.com.api.service.BoardService;
import egovframework.com.api.service.GovSupportService;
import egovframework.com.api.service.MemberService;
import egovframework.com.api.service.MngBannerService;
import egovframework.com.api.service.MngPopupService;
import egovframework.com.api.service.MngService;
import egovframework.com.api.service.WorkService;
import egovframework.com.api.vo.AuctionVO;
import egovframework.com.api.vo.BoardVO;
import egovframework.com.api.vo.ResponseEntity;
import egovframework.com.api.vo.WorkVO;
import egovframework.com.cmm.util.EgovWebUtil;
import io.swagger.annotations.Api;

@Api(tags = "7. Member APIs")
@RestController
@RequestMapping(value = UrlConstant.API_V1)
public class MemberController {

	@Resource(name = "memberService")
	private MemberService memberService;
	@Resource(name="mngService")
	private MngService mngService;
	@Resource(name="auctionService")
	private AuctionService auctionService;
	@Resource(name="artfairService")
	private ArtfairService artfairService;
	@Resource(name="workService")
	private WorkService workService;
	@Resource(name = "mngBannerService")
	private MngBannerService bannerService;
	@Resource(name="boardService")
	private BoardService boardService;
	@Resource(name = "mngPopupService")
	private MngPopupService popupService;
	@Resource(name = "govSupportService")
	private GovSupportService govSupportService;

	@GetMapping({"/kart/main"})
	public ResponseEntity<MainListRes> displayIndex(HttpServletRequest request) throws Exception {

		String siteUrl = EgovWebUtil.getSiteUrl(request.getRequestURI(), request);
//		Calendar today = Calendar.getInstance();
//		DateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");

		memberService.insertGuestStats();

		// 화랑 10개 랜덤
//		MemberVO memberVO = new MemberVO();
//		memberVO.setSortKey("random");
//		memberVO.setFile_grp("imageOrg");
//		memberVO.setFirstIndex(1);
//		memberVO.setLastIndex(10);
//		memberVO.setSearchOrgType("GA");
//		List<MemberVO> galleryList = mngService.selectMemberOrgList(memberVO);
//		List<GaleryRes> galleries = new ArrayList<>();
//		for (MemberVO item : galleryList) {
////			galleries.add(ObjectUtil.copyFromObject(item, GaleryRes.class));
//			System.out.println("member_id: " + item.getMember_id());
//			System.out.println("login_id: " + item.getLogin_id());
//			System.out.println("org_nm: " + item.getOrg_nm());
//			System.out.println("org_nm_en: " + item.getOrg_nm_en());
//			System.out.println("file_id: " + item.getFile_id());
//			System.out.println("member_nm: " + item.getMember_nm());
//			System.out.println("memberNm: " + item.getMemberNm());
//		}
//		mainResponse.setGalleries(galleries);

		// 진행중인 경매&전시 (2020-07-29 추가)
		AuctionVO auction_ing = new AuctionVO();
		auction_ing.setCnt(4); //기간 종료일 기준 30개 조회
		List<AuctionVO> auctexhi_list = auctionService.selectAuctExhiList(auction_ing);
		
		// 아트페어 - 진행, 예정, 종료 순으로 5개
//		ArtfairVO artfairVO = new ArtfairVO();
//		artfairVO.setSearchOpenYn("Y");
//		artfairVO.setSortKey("topsort");
//		artfairVO.setFile_grp("thumb00");
//		artfairVO.setFirstIndex(1);
//		artfairVO.setLastIndex(5);
//		List<ArtfairVO> artfairList = artfairService.selectArtfairList(artfairVO);
//		mainResponse.setArtfairs(artfairList);

		// 작품 - 랜덤 25개 (2020-07-29)
		WorkVO workVO = new WorkVO();
		workVO.setCnt(4);
		List<WorkVO> workList = workService.selectWorkMainList(workVO);

		// 최신 낙찰 작품정보
		List<WorkVO> workLatestList = workService.selectWorkHistoryWithoutWorkIdList(workVO);

		// 배너
//		BannerVO bannerVO = new BannerVO();
//		bannerVO.setSearchOpenYn("Y");
//		bannerVO.setSearchSite("KOREAN");
//		if("/english".equals(siteUrl) || "/m/eng".equals(siteUrl)) bannerVO.setSearchSite("ENGLISH");
//		List<BannerVO> bannerList = bannerService.selectBannerListOnly(bannerVO);
//		mainResponse.setBanners(bannerList);

		BoardVO boardVO = new BoardVO();
		boardVO.setSearchOpenYn("Y");

		// 시장리포트5개
		boardVO.setBoardMstId("BRD_MST_ID00001");
		boardVO.setBoardCd("Report");
		if("/english".equals(siteUrl) || "/m/eng".equals(siteUrl)){
			boardVO.setBoardMstId("BRD_MST_ID00010");
			boardVO.setBoardCd("EngReport");
		}
		boardVO.setFirstIndex(1);
		boardVO.setLastIndex(5);
		boardVO.setSortKey("REG_DT");
		List<BoardVO> reportList = boardService.selectBoardList(boardVO);

		// 시장분석 5개
		boardVO.setBoardMstId("BRD_MST_ID00002");
		boardVO.setBoardCd("Analysis");

		if("/english".equals(siteUrl) || "/m/eng".equals(siteUrl)){
			boardVO.setBoardMstId("BRD_MST_ID00011");
			boardVO.setBoardCd("EngEtc");
		}
		boardVO.setFirstIndex(1);
		boardVO.setLastIndex(5);
		boardVO.setSortKey("REG_DT");
//		List<BoardVO> etcList = boardService.selectBoardList(boardVO);
//		mainResponse.setEtcs(etcList);

		// 공지사항 5개 BRD_MST_ID00012
		boardVO.setBoardMstId("BRD_MST_ID00005");
		boardVO.setBoardCd("Notice");
		if("/english".equals(siteUrl) || "/m/eng".equals(siteUrl)){
			boardVO.setBoardMstId("BRD_MST_ID00012");
			boardVO.setBoardCd("EngNotice");
		}
//		boardVO.setFirstIndex(1);
//		boardVO.setLastIndex(5);
//		boardVO.setSortKey("REG_DT");
//		List<BoardVO> noticeList = boardService.selectBoardList(boardVO);
//		mainResponse.setNotices(noticeList);

		// 시장뉴스 8개 BRD_MST_ID00007
		boardVO.setBoardMstId("BRD_MST_ID00007");
		boardVO.setBoardCd("News");
		boardVO.setFirstIndex(1);
		boardVO.setLastIndex(3);
		boardVO.setSortKey("REG_DT");
		List<BoardVO> newsList = boardService.selectBoardList(boardVO);

		// 팝업리스트
//		PopupVO popupVO = new PopupVO();
//		popupVO.setSearchStatus("ing");
//		popupVO.setLastIndex(100);
//		popupVO.setSearchSite("KOREAN");
//		if("/english".equals(siteUrl) || "/m/eng".equals(siteUrl)) popupVO.setSearchSite("ENGLISH");
//		List<PopupVO> popupList = popupService.selectPopupList(popupVO);
//		mainResponse.setPopups(popupList);

		//시각예술 공모 현황
//		SupportBusinessStateVO supportBusinessVO = new SupportBusinessStateVO();
//		supportBusinessVO.setCnt(3); // 출력수
//		List<SupportBusinessStateVO> suppBusiList = govSupportService.selectSupportBusinessMainList(supportBusinessVO);
//		mainResponse.setSuppBusiness(suppBusiList);
		MainListRes mainListRes = new MainListRes(workLatestList, workList, auctexhi_list, reportList, newsList, request.getContextPath());

		return new ResponseEntity<>(HttpStatus.SC_OK, "OK", mainListRes);
	}
}
