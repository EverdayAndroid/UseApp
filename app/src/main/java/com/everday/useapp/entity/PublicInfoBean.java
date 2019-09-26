package com.everday.useapp.entity;

public class PublicInfoBean {

    /**
     * msg : OK
     * code : 200
     * data : {"state":"ok","url":"https://smlfront.esign.cn:8820?context=d8Hd%2FsOGoFUIi081hs1hhnrNKhcza7yQ3YEzhRkD2n6k3i5dBOEs2gd6UexD5f3bJQWYsOj5KnX0VTCsOrCZ4w6xcfWFEWTtNa4FvfE%2Fe%2Fe81aRhVPRnw7VsndYIa7%2FsZ5IUEm3ZFjnOB1gv4cOn8CRKOGv%2BCn5rtxB288vf0A6ELpBrZ3HZYLVBqi9Wqjt8pcO5H2upaxKDwGYQurorKEVUk6gGIn0bdaEWw0oCJkW%2FjITdJfeTJYf3EZtilsfEiti6oHuOuNvFClmLa5ti6iynbBAb3hfbQ0eConeC%2FStQYfNpgzevJgQyAnE3TlZHZPRUfYr7B3tvYNM66KEXYd%2FdMETcoFckJNaCwXhMabP72fyX91FAzdg8EWOF3AKY&flowId=a5b791cfc55f4ec181afbc5f1adff1bc&organ=false&tsign_source_type=SIGN_LINK_WUKONG&tsign_source_detail=16R2mv%2F27h2Y5CkM9bwhboM3UID%2FY0Al%2FZtqpbzSLvTAzZZVFyERoqyTGPX0ssjyL%2BSDjvyYGh1ez1Pxme2N%2FTimhV7UXinJKMhj90vEA1GjsQXUR6m44pL0yChsm0ylAo1beKGU7qxVjhjRuOT2j1pNb24cgzLRWvESb2J9XfKnrL5IC%2BKOMr7ebMP9WC8QiMopc%2BJ5VUjJ1GQI1MxFmRAAxaaC6OfCxxOS95j3oxew%3D"}
     */

    private String msg;
    private String code;
    private PublicBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public PublicBean getData() {
        return data;
    }
}
