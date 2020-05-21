package com.saturn.action.carwebcam.appcarwebcam;

import com.saturn.app.utils.BeanUtils;
import com.saturn.app.web.IAction;
import com.saturn.app.web.IView;
import com.saturn.app.web.view.JsonView;
import com.saturn.domain.carwebcam.CarWebCam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: caojinxin
 * Date: 16-11-10
 * Time: 上午10:20
 * 手机端 车辆信息列表action
 */
public class AppCarListAction implements IAction {
    @Override
    public IView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        CarWebCam vo = BeanUtils.getBean(request, CarWebCam.class);
        String cargroupId = request.getParameter("cargroup_id");
        String orgId = request.getParameter("orgId");//部门根id
        String page_no = request.getParameter("page_no");//部门根id
        StringBuffer caseString = new StringBuffer();    //返回车辆数据字符串
        int listCount = CarWebCam.carListForAppCount(vo,orgId,cargroupId);
        caseString.append("{\"count\":\""+listCount+"\",\"list\":[");
        List<Map> list = CarWebCam.carListForApp(vo,orgId,cargroupId,page_no);
        if(list!=null){
            for (int i = 0; i < list.size(); i++) {
                if (i == list.size() - 1) {
                    caseString.append("{\"id\":\"" + list.get(i).get("id") + "\",\"ssjg_name\":\"" + list.get(i).get("ssjg_name") + "\",\"cllx_name\":\"" + list.get(i).get("cllx_name") + "\",\"c_name\":\"" + list.get(i).get("c_name") + "\",\"carcode\":\"" + list.get(i).get("carcode") + "\",\"carid\":\"" + list.get(i).get("carid") + "\",\"device_index_code\":\"" + list.get(i).get("device_index_code") + "\",\"nbglbh\":\"" + list.get(i).get("nbglbh") + "\"}");
                } else {
                    caseString.append("{\"id\":\"" + list.get(i).get("id") + "\",\"ssjg_name\":\"" + list.get(i).get("ssjg_name") + "\",\"cllx_name\":\"" + list.get(i).get("cllx_name") + "\",\"c_name\":\"" + list.get(i).get("c_name") + "\",\"carcode\":\"" + list.get(i).get("carcode") + "\",\"carid\":\"" + list.get(i).get("carid") + "\",\"device_index_code\":\"" + list.get(i).get("device_index_code") + "\",\"nbglbh\":\"" + list.get(i).get("nbglbh") + "\"},");
                }
            }
            caseString.append("]}");
            return new JsonView(caseString.toString());
        }else{
            return new JsonView("[]");
        }
    }

    @Override
    public String requestMapping() {
        return "/carwebcam/appcarwebcam/AppCarList.met";
    }
}
