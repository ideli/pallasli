var shineyue = shineyue || {};
(function($) {
	shineyue.resouces = [ {
		source_name : "unit_change",
		js_url : "pages/unit/unit_change.js",
		html_url : "pages/unit/unit_change.html",
		nav_path : "单位业务>>单位信息变更"
	}, {
		source_name : "password_change",
		js_url : "pages/personal/password_change.js",
		html_url : "pages/personal/password_change.html",
		nav_path : "个人业务>>密码修改"
	} ,{
		source_name : "person_applications_query",
		js_url : "pages/personal/person_applications_query.js",
		html_url : "pages/personal/person_applications_query.html",
		nav_path : "个人业务>>申请查询"
	},{
		source_name : "unit_hjhd",
		js_url : "pages/unit/unit_hjhd.js",
		html_url : "pages/unit/unit_hjhd.html",
		nav_path : "单位业务>>单位汇缴管理"
	}, {
		source_name : "unit_hjhd_byzj",
		js_url : "pages/unit/unit_change.js",
		html_url : "pages/unit/unit_hjhd_byzj.html",
		nav_path : "单位业务>>单位缴款管理"
	}, {
		source_name : "unit_ndjstz",
		js_url : "pages/unit/unit_ndjstz.js",
		html_url : "pages/unit/unit_ndjstz.html",
		text : "单位业务>>年度基数调整"
	}, {
		source_name : "unit_information_query",
		js_url : "pages/unit/unit_information_query.js",
		html_url : "pages/unit/unit_information_query.html",
		nav_path : "单位业务>>单位信息查询"
	}, {
		source_name : "unit_hjhd_byjs",
		js_url : "pages/unit/unit_information_query.js",
		html_url : "pages/unit/unit_hjhd_byjs.html",
		nav_path : "单位业务>>单位汇缴核定>>本月减少"
	}, {
		source_name : "unit_hjhd_byzj",
		js_url : "pages/unit/unit_information_query.js",
		html_url : "pages/unit/unit_hjhd_byzj.html",
		nav_path : "单位业务>>单位汇缴核定>>本月增加"
	}, {
		source_name : "person_change",
		js_url : "pages/personal/person_change.js",
		html_url : "pages/personal/person_change.html",
		nav_path : "个人业务>>个人信息变更"
	}, {
		source_name : "person_extract",
		js_url : "pages/personal/person_extract.js",
		html_url : "pages/personal/person_extract.html",
		nav_path : "个人业务>>个人提取"
	}, {
		source_name : "person_personalloans_applicant",
		js_url : "pages/personal/person_personalloans_applicant.js",
		html_url : "pages/personal/person_personalloans_applicant.html",
		nav_path : "个人业务>>申请受理"
	}, {
		source_name : "person_monthlyhedge_zhxx",
		js_url : "pages/personal/person_monthlyhedge_zhxx.js",
		html_url : "pages/personal/person_monthlyhedge_zhxx.html",
		nav_path : "个人业务>>月对冲"
	}, {
		source_name : "person_information_query",
		js_url : "pages/personal/person_information_query.js",
		html_url : "pages/personal/person_information_query.html",
		nav_path : "个人业务>>个人查询"
	}, {
		source_name : "person_extract_housing_fwxx",
		html_url : "pages/personal/person_extract_housing_fwxx.html",
		js_url : "pages/personal/person_extract_housing_fwxx.js",
		nav_path : "个人业务>>个人提取>>房屋信息"
	}, {
		source_name : "person_extract_housing_tqxx",
		html_url : "pages/personal/person_extract_housing_tqxx.html",
		js_url : "pages/personal/person_extract_housing_tqxx.js",
		nav_path : "个人业务>>个人提取>>部分提取"
	}, {
		source_name : "person_extract_housing_tqyy",
		html_url : "pages/personal/person_extract_housing_tqyy.html",
		js_url : "pages/personal/person_extract_housing_tqyy.js",
		nav_path : "个人业务>>个人提取>>提交预约"
	}, {
		source_name : "person_extract_renting_housing_fwxx",
		html_url : "pages/personal/person_extract_renting_housing_fwxx.html",
		js_url : "pages/personal/person_extract_renting_housing_fwxx.js",
		nav_path : "个人业务>>个人提取>>租房信息"
	}, {
		source_name : "person_extract_renting_housing_tqxx",
		html_url : "pages/personal/person_extract_renting_housing_tqxx.html",
		js_url : "pages/personal/person_extract_renting_housing_tqxx.js",
		nav_path : "个人业务>>个人提取>>部分提取"
	}, {
		source_name : "person_extract_renting_housing_tqyy",
		html_url : "pages/personal/person_extract_renting_housing_tqyy.html",
		js_url : "pages/personal/person_extract_renting_housing_tqyy.js",
		nav_path : "个人业务>>个人提取>>提交预约"
	}, {
		source_name : "person_repayment",
		html_url : "pages/personal/person_repayment.html",
		js_url : "pages/personal/person_repayment.js",
		nav_path : "个人业务>>还款"

	},{
		source_name : "calculator",
		html_url : "pages/personal/calculator.html",
		js_url : "pages/personal/calculator.js",
		nav_path : "个人业务>>提前还款计算器"

		
	}, {
		source_name : "person_extract_renting_housing_fwxx",
		html_url : "pages/personal/person_extract_renting_housing_fwxx.html",
		js_url : "pages/personal/person_extract_renting_housing_fwxx.js"
		
	}, {
		source_name : "person_extract_renting_housing_tqxx",
		html_url : "pages/personal/person_extract_renting_housing_tqxx.html",
		js_url : "pages/personal/person_extract_renting_housing_tqxx.js"
		
	}, {
		source_name : "person_extract_ cloaccount_tqxx",
		html_url : "pages/personal/person_extract_ cloaccount_tqxx.html",
		js_url : "pages/personal/person_extract_ cloaccount_tqxx.js"
		
	}, {
	source_name : "person_extract_renting_housing_fwxx",
	html_url : "pages/personal/person_extract_renting_housing_fwxx.html",
	js_url : "pages/personal/person_extract_renting_housing_fwxx.js"
	
}, {
	source_name : "person_extract_renting_housing_tqxx",
	html_url : "pages/personal/person_extract_renting_housing_tqxx.html",
	js_url : "pages/personal/person_extract_renting_housing_tqxx.js"
	
},{
	source_name : "person_personalloans_housing_yytj",
	html_url : "pages/personal/person_personalloans_housing_yytj.html",
	js_url : "pages/personal/person_personalloans_housing_yytj.js",
	nav_path : "个人业务>>申请受理>>预约提交(商品房)"
},{
	source_name : "person_personalloans_housingsituation",
	html_url : "pages/personal/person_personalloans_housingsituation.html",
	js_url : "pages/personal/person_personalloans_housingsituation.js",
	nav_path : "个人业务>>申请受理>>购房情况(商品)"
},{
	source_name : "person_personalloans_loancase",
	html_url : "pages/personal/person_personalloans_loancase.html",
	js_url : "pages/personal/person_personalloans_loancase.js",
	nav_path : "个人业务>>申请受理>>申贷情况"
},{
	source_name : "person_personalloans_secondhousing_yytj",
	html_url : "pages/personal/person_personalloans_secondhousing_yytj.html",
	js_url : "pages/personal/person_personalloans_secondhousing_yytj.js",
	nav_path : "个人业务>>申请受理>>预约提交(二手房)"
},{
	source_name : "person_personalloans_secondhousingsituation",
	html_url : "pages/personal/person_personalloans_secondhousingsituation.html",
	js_url : "pages/personal/person_personalloans_secondhousingsituation.js",
	nav_path : "个人业务>>申请受理>>购房情况(二手)"
}, {
	source_name : "person_extract_cloaccount_tqxx",
	html_url : "pages/personal/person_extract_cloaccount_tqxx.html",
	js_url : "pages/personal/person_extract_cloaccount_tqxx.js",
	nav_path : "个人业务>>个人提取>>提取信息"
}, {
	source_name : "person_extract_cloaccount_zjsc",
	html_url : "pages/personal/person_extract_cloaccount_zjsc.html",
	js_url : "pages/personal/person_extract_cloaccount_zjsc.js",
	nav_path : "个人业务>>个人提取>>证件上传"
}, {
	source_name : "person_extract_cloaccount_zzldgx_zjsc",
	html_url : "pages/personal/person_extract_cloaccount_zzldgx_zjsc.html",
	js_url : "pages/personal/person_extract_cloaccount_zzldgx_zjsc.js",
	nav_path : "个人业务>>个人提取>>证件上传"
}, {
	source_name : "developers_slxx",
	html_url : "pages/developers/developers_slxx",
	js_url : "pages/personal/developers_slxx.js",
	nav_path : "开发商>>申请受理"
}, {
	source_name : "person_extract_cloaccount_tqyy",
	html_url : "pages/personal/person_extract_cloaccount_tqyy.html",
	js_url : "pages/personal/person_extract_cloaccount_tqyy.js",
	nav_path : "个人业务>>个人提取>>提交预约"
}, {
	source_name : "person_extract_cloaccount_zzldgx_tqyy",
	html_url : "pages/personal/person_extract_cloaccount_zzldgx_tqyy.html",
	js_url : "pages/personal/person_extract_cloaccount_zzldgx_tqyy.js",
	nav_path : "个人业务>>个人提取>>提交预约"
}, {
	source_name : "person_extractelse_tqyy",
	html_url : "pages/personal/person_extractelse_tqyy.html",
	js_url : "pages/personal/person_extractelse_tqyy.js",
	nav_path : "个人业务>>个人提取>>提交预约"
}, {
	source_name : "person_extractelse_tqje",
	html_url : "pages/personal/person_extractelse_tqje.html",
	js_url : "pages/personal/person_extractelse_tqje.js",
	nav_path : "个人业务>>个人提取>>提取金额"
}, {
	source_name : "person_applications_query",
	html_url : "pages/personal/person_applications_query.html",
	js_url : "pages/personal/person_extractelse_tqje.js",
	nav_path : "个人业务>>个人申请查询"
}, {
	source_name : "person_loan_application",
	html_url : "pages/personal/person_loan_application.html",
	js_url : "pages/personal/person_extractelse_tqje.js",
	nav_path : "个人业务>>贷款申请"
}, {
	source_name : "developers_basicinformation_inquiry"
}, {
	source_name : "developers_buildinginformation_input"
}, {
	source_name : "developers_buildinginformation_inquiry"
}, {
	source_name : "developers_hoseinformation_input"
}, {
	source_name : "developers_hoseinformation_inquiry"
}, {
	source_name : "developers_loanapplication"
}, {
	source_name : "developers_loanapplication_inquiry"
}, {
	source_name : "developers_loaninformation_inquiry"
}, {
	source_name : "developers_txqz_input"
}, {
	source_name : "unit_loaninformation_changes"
}, {
	source_name : "unit_loaninformation_inquiry"
}, {
	source_name : "unit_loanapplication_inquiry"
}, {
	source_name : "unit_businessapply_inquiry"
}, {
	source_name : "unit_contributions"
}, {
	source_name : "unit_extraction_application"
}, {
	source_name : "unit_internaltransfer"
}, {
	source_name : "unit_loan_application"
}, {
	source_name : "unit_monthapply",
	js_url : "pages/unit/unit_monthapply.js"
}, {
	source_name : "unit_pay"
}, {
	source_name : "unit_personal_information_inquiry",
	js_url : "pages/unit/unit_personal_information_inquiry.js"
}, {
	source_name : "unit_personalchangs"
}, {
	source_name : "unit_repayment",
	html_url : "pages/unit/unit_repayment.html",
	js_url : "pages/unit/unit_repayment.js",
	nav_path : "单位业务>>提前还款提醒"
}, {
	source_name : "unti_internal_transferout"
}, {
	source_name : "lct"
}, {
	source_name : "unit_calculator",
	js_url : "pages/unit/unit_calculator.js"
}, {
	source_name : "unit_personalloans_decoration_yytj",
	html_url : "pages/unit/unit_personalloans_decoration_yytj.html",
	js_url : "pages/unit/unit_personalloans_decoration_yytj.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_housing_yytj",
	html_url : "pages/unit/unit_personalloans_housing_yytj.html",
	js_url : "pages/unit/unit_personalloans_housing_yytj.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_housingsituation",
	html_url : "pages/unit/unit_personalloans_housingsituation.html",
	js_url : "pages/unit/unit_personalloans_housingsituation.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_loancase",
	html_url : "pages/unit/unit_personalloans_loancase.html",
	js_url : "pages/unit/unit_personalloans_loancase.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_secondhousing_yytj",
	html_url : "pages/unit/unit_personalloans_secondhousing_yytj.html",
	js_url : "pages/unit/unit_personalloans_secondhousing_yytj.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_secondhousingsituation",
	html_url : "pages/unit/unit_personalloans_secondhousingsituation.html",
	js_url : "pages/unit/unit_personalloans_secondhousingsituation.js",
	nav_path : "单位业务>>申请受理"
}, {
	source_name : "unit_personalloans_sqrxx",
	html_url : "pages/unit/unit_personalloans_sqrxx.html",
	js_url : "pages/unit/unit_personalloans_sqrxx.js",
	nav_path : "单位业务>>申请受理"
}];
	 
	shineyue.getSource = function(sourceName) {
		for ( var i in shineyue.resouces) {
			if (shineyue.resouces[i].source_name == sourceName) {
				return shineyue.resouces[i];
			}
		}
		return;
	};

})();