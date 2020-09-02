function __CreateJSPath(js) {
	var scripts = document.getElementsByTagName("script");
	var path = "";
	for (var i = 0, l = scripts.length; i < l; i++) {
		var src = scripts[i].src;
		if (src.indexOf(js) != -1) {
			var ss = src.split(js);
			path = ss[0];
			break;
		}
	}
	var href = location.href;
	href = href.split("#")[0];
	href = href.split("?")[0];
	var ss = href.split("/");
	ss.length = ss.length - 1;
	href = ss.join("/");
	if (path.indexOf("https:") == -1 && path.indexOf("http:") == -1 && path.indexOf("file:") == -1 && path.indexOf("\/") != 0) {
		path = href + "/" + path;
	}
	return path;
}

//miniui（不要使用jquery2.0以上版本，否则不兼容IE8）
//document.write('<script src="' + bootPATH + 'LodopFuncs.js" type="text/javascript" ></sc' + 'ript>');
(function(){
	var bootPATH = __CreateJSPath("LodopPrint.js");
	var head = document.head || document.getElementsByTagName("head")[0] || document.documentElement;
	var LodopFuncsJs = document.createElement("script");
	LodopFuncsJs.src = bootPATH + 'LodopFuncs.js';
	head.insertBefore(LodopFuncsJs, head.firstChild);
})();


//打印标签
function printLabel(packs,methed,from){
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	//
	var url ="/production/productionSite/productInWarehouse/getInList/{0}".format(packs);
	if(from=="STOCK"){
		url ="/ck/yw/warehouseKc/getPrintPackList/{0}".format(packs);
	}
	$['post'](url, function (res) {
        if (200 === res.code) {
        	var data = res.data;
        	for( var i=0;i<data.length;i++){
        		//LODOP.PRINT_INITA("0mm","0mm","50.01mm","30.01mm","库存"); 
        		if(data[i].inType=="YL"){
	        		LODOP.NewPage();
	        		var QR =data[i].inPack+'#'+data[i].inCode+'#'+data[i].cpcodeName+'#'+data[i].cpcodeSize+'#'+data[i].inLuono;
	        		LODOP.SET_PRINT_PAGESIZE(1,500,300,""); //纸张大小   
	        		LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);                       
	        		LODOP.ADD_PRINT_BARCODE("4.00mm","1.50mm","25mm","25mm","QRCode",QR);
	        		LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",5);
	        		/*LODOP.ADD_PRINT_TEXT("4.00mm","26.22mm","63.5mm","6.61mm",data[i].inCode);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10); */
	        		LODOP.ADD_PRINT_TEXT("4.00mm","24.00mm","65.5mm","6.61mm",data[i].cpcodeName);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");               
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",9); 
	        		LODOP.ADD_PRINT_TEXT("8.50mm","24.00mm","65.5mm","6.61mm",data[i].cpcodeSize);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");               
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
	        		LODOP.SET_PRINT_STYLEA(0,"Bold","1");   
	        		LODOP.ADD_PRINT_TEXT("12.00mm","24.00mm","65.5mm","6.61mm",data[i].inSl+' '+nullif(data[i].cpcodeBz,""));
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",9); 
	        		LODOP.SET_PRINT_STYLEA(0,"Bold","1");   
	        		LODOP.ADD_PRINT_TEXT("15.50mm","24.00mm","65.5mm","6.61mm",data[i].inLuono);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");               
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",9);
	        		LODOP.ADD_PRINT_TEXT("19.00mm","23.00mm","65.5mm","6.61mm",data[i].inPack);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",8);   
        		}else{
	        		LODOP.NewPage();
	        		var QR =data[i].inPack+'#'+data[i].inCode+'#'+data[i].cpcodeName+'#'+data[i].cpcodeSize+'#'+data[i].cpcodeFl;
	        		LODOP.SET_PRINT_PAGESIZE(1,500,400,""); //纸张大小   
	        		LODOP.SET_PRINT_MODE("PRINT_NOCOLLATE",1);                       
	        		LODOP.ADD_PRINT_BARCODE("4.00mm","3.50mm","25mm","25mm","QRCode",QR);
	        		LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",5);
	        		LODOP.ADD_PRINT_TEXT("4.00mm","26.22mm","63.5mm","6.61mm",data[i].inCode);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10); 
	        		LODOP.ADD_PRINT_TEXT("9.50mm","26.22mm","60.85mm","6.61mm",data[i].cpcodeFl);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);   
	        		LODOP.ADD_PRINT_TEXT("15.00mm","26.22mm","63.5mm","6.61mm",data[i].inSl+' '+nullif(data[i].cpcodeBz,""));
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10); 
	        		LODOP.ADD_PRINT_TEXT("20.50mm","26.22mm","63.5mm","6.61mm",data[i].cpcodeXp);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");              
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);        
	        		LODOP.ADD_PRINT_TEXT("26.00mm","5.00mm","63.5mm","6.61mm",data[i].cpcodeName);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");               
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10); 
	        		LODOP.ADD_PRINT_TEXT("26.00mm","32.00mm","63.5mm","6.61mm",data[i].cpcodeSize);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");               
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10); 
	        		LODOP.ADD_PRINT_TEXT("31.50mm","10.00mm","63.5mm","6.61mm",data[i].inPack);
	        		LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑 Light");    
	        		LODOP.SET_PRINT_STYLEA(0,"FontSize",10);   
	        		
        		}
        	}
        	if(methed=="PREVIEW"){
        		LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);
        		//LODOP.PREVIEW();
        		LODOP.SET_PREVIEW_WINDOW(1,2,1,500,400,'');
        		LODOP.PREVIEW();
        		//LODOP.PREVIEW("_dialog",500,400,'');
        	}else if(methed=="PRINT"){
        		LODOP.PRINT();
        	}else{
        		LODOP.PRINTA();
        	}
        } else {
            hmq.tipDanger(res.msg);
        }
    }); 
	
}

//打印工艺卡
function printGyk(packs,methed,parms){
	LODOP=getLodop(document.getElementById('LODOP_OB'),document.getElementById('LODOP_EM'));
	var url ="/sc/task/ykbg/getInList/{0}".format(packs);
	$['post'](url, function (res) {

		if (200 === res.code) {
			var data = res.data;
			var heightstr=40;
			var sumheightstr=0;
			for( var i=0;i<data.length;i++){
				if(i==0){
					LODOP.NewPage();
				var QR =data[0].bgc+'#'+data[0].cpcode+'#'+data[0].cpname+'#'+data[0].cpsize+'#'+data[0].cpfl;
				LODOP.SET_PRINT_PAGESIZE(1,500,800,""); //纸张大小
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",true);
				LODOP.ADD_PRINT_BARCODE(16,26,"25mm","25mm","QRCode",QR);
				LODOP.SET_PRINT_STYLEA(0,"QRCodeVersion",5);

					LODOP.ADD_PRINT_TEXT(15,122,35,25,"订单");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(42,122,35,25,"品名");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(15,298,35,25,"种类");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(42,298,35,25,"规格");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(69,122,35,25,"炉号");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

					LODOP.ADD_PRINT_TEXT(15,157,135,25,data[0].ht_mo);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(15,333,150,25,data[0].cpfl);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);

					LODOP.ADD_PRINT_TEXT(42,333,150,25,data[0].cpsize);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(42,157,135,25,data[0].cpname);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(69,158,325,25,data[0].e_pz_luhao);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.ADD_PRINT_TEXT(92,19,104,22,data[0].bgc);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",8);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

					LODOP.ADD_PRINT_TEXT(115,28,40,27,"序号");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(115,69,101,27,"工序");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(115,175,64,27,"产品");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(115,241,69,27,"数量");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(115,315,82,27,"时间");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(115,396,78,27,"人员");
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

					LODOP.ADD_PRINT_LINE(145,27,146,477,0,1);//直线

					LODOP.ADD_PRINT_TEXT(155,28,40,27,(i+1));
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155,69,101,27,data[i].gx);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155,175,64,27,data[i].tsize);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155,241,69,27,data[i].tsl);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
					LODOP.ADD_PRINT_TEXT(155,315,82,27,data[i].bgc_wc);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155,396,78,27,data[i].do_man);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
				}else {
					LODOP.ADD_PRINT_LINE(145+(heightstr*i),34,146+(heightstr*i),477,0,1);//直线
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),28,40,27,(i+1));
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),69,101,27,data[i].gx);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),175,64,27,data[i].tsize);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),241,69,27,data[i].tsl);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",3);
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),315,82,27,data[i].bgc_wc);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);
					LODOP.ADD_PRINT_TEXT(155+(heightstr*i),396,78,27,data[i].do_man);
					LODOP.SET_PRINT_STYLEA(0,"FontName","微软雅黑");
					LODOP.SET_PRINT_STYLEA(0,"FontSize",10);
					LODOP.SET_PRINT_STYLEA(0,"Alignment",2);

				}
			}
			LODOP.ADD_PRINT_LINE(145+heightstr*(data.length),34,146+heightstr*(data.length),477,0,1);//直线
			if(methed=="PREVIEW"){
				LODOP.SET_PRINT_MODE("AUTO_CLOSE_PREWINDOW",1);

				LODOP.SET_PREVIEW_WINDOW(1,2,1,550,400,'');
				//LODOP.PRINT_DESIGN();//设计模式
				LODOP.PREVIEW();
				//LODOP.PREVIEW("_dialog",500,400,'');
			}else if(methed=="PRINT"){
				LODOP.PRINT();
			}else{
				LODOP.PRINTA();
			}

		} else {
			hmq.tipDanger(res.msg);
		}
	});

}

function nullif(val, outVal) {
    if (isNull(val)) {
        return outVal;
    } else {
        return val;
    }
}