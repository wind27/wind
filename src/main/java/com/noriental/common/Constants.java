package com.noriental.common;

/**
 * Global Constants
 *
 * @author Wang Beichen
 * @date 2014-1-16
 * @version 1.0
 */
public class Constants {
    public static class OP {
        public static final String GET = ".findById";
        public static final String INSERT = ".insert";
        public static final String UPDATE = ".update";
        public static final String DELETE = ".delete";
    }
	
	public static class PagerSize {	
		public static final int DEFAULT = 10;	
		public static final int SUBJECT = 10;		
		public static final int COURSE = 20;
		public static final int EXAM = 10;	
		public static final int QUESTION = 10;	
		public static final int COMMENT = 10;
		public static final int USER = 30;
		public static final int SCHOOL_SYSTEM = 30;
	}
	
	public static enum LoginType {
        //分享平台
        share,
        //教师门户
        teacher,
        //学生门户
        student,
        //学校门户
        school,
        //区域管理
        director,
        //运营系统
        admin,
        //公立教师空间
        publicTeacher,
        //集团教师空间
        privateTeacher,
        
        //学生
        publicStudent,
        privateStudent,

        //家长
        publicPatriarch,
        privatePatriarch
    }
    public static class Md5Key {
        public static final String PORTAL_ADMIN = "#DsjW2014@xDf.AdMin.cn#";
        public static final String SCHOOL_PORTAL = "#DsjW@2014XDf.Web.cn#";
    }
    public static final String METADATA = "Metadata";
}
