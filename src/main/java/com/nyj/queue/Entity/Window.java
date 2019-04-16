package com.nyj.queue.Entity;

/**
 * @author : Ni Yujia
 * @date : 2019/4/13
 */
@SuppressWarnings("ALL")
public enum Window {

    EYE,EAR,MOUTH,ECG,BLOOD,LIVER,BSCAN;
    @Override
    public String toString() {
        String name = null;
        switch (this) {
            case EYE:
                name = "眼科";
                break;
            case EAR:
                name = "耳科";
                break;
            case MOUTH:
                name = "口腔科";
                break;
            case ECG:
                name = "心电图";
                break;
            case BLOOD:
                name = "血常规";
                break;
            case LIVER:
                name = "肝功能";
                break;
            case BSCAN:
                name = "B超";
                break;
            default:
        }
        return name;
    }
}
