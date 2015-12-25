<%@ page language="java" errorPage="/error.jsp" pageEncoding="UTF-8"
	contentType="text/html;charset=utf-8"%>
<%@ include file="/common/taglibs.jsp"%>
<html>
	<head>

		<title>学习平台</title>


		<style type="text/css">
.topDivOfJsp .tbarDivOfJsp {
	text-align: right;
	padding-right: 100px;
	font-size: 16;
}

.topDivOfJsp .titleDivOfJsp {
	text-align: center;
	font-size: 28;
	padding-top: 20px;
}

.topDivOfJsp .tbarDivOfJsp a {
	padding-left: 20px;
}

.leftDivOfJsp {
	text-align: left;
	float: left;
	width: 1000px;
	padding-left: 100px;
}

.rightDivOfJsp {
	text-align: left;
	float: right;
	min-width: 200px;
}

.fieldDiv {
	text-align: left;
	float: left;
	width: 280px;
	height: 150px;
	margin-left: 50px;
}

.stepDiv {
	text-align: left;
	float: left;
	width: 280px;
	height: 150px;
	margin-left: 50px;
}

.experimentDiv {
	text-align: left;
	float: left;
	width: 280px;
	height: 150px;
	margin-left: 50px;
}
</style>
	</head>
	<body style="overflow: scroll">

		<div class='topDivOfJsp'>
			<div class="titleDivOfJsp">
				理学工作室 学习平台
			</div>
			<div class="tbarDivOfJsp" id="tbar-div">
				<a href="learningPlat/firstPage.jsp">学习平台</a><a
					href="systemManage/login.jsp">系统管理</a>
			</div>
		</div>
		<div>
			<div class="leftDivOfJsp">



				<fieldset>
					<legend>
						初中
					</legend>

					<div class="middleSchoolDiv">
						<div class="fieldDiv">
							<fieldset>
								<legend>
									领域
								</legend>
								<ul>
									<li>
										<a href="middleSchool/physics/mechanics/firstPage.jsp">力学</a>
									</li>
									<li>
										<a href="middleSchool/physics/calorifics/firstPage.jsp">热学</a>
									</li>
									<li>
										<a href="middleSchool/physics/acoustics/firstPage.jsp">声学</a>
									</li>
									<li>
										<a href="middleSchool/physics/optics/firstPage.jsp">光学</a>
									</li>
									<li>
										<a href="middleSchool/physics/electromagnetics/firstPage.jsp">电磁学</a>
									</li>
									<li>
										<a href="middleSchool/physics/micro/firstPage.jsp">微观世界</a>
									</li>
								</ul>
							</fieldset>

						</div>
						<div class="stepDiv">
							<fieldset>
								<legend>
									进阶课程
								</legend>
								<ul>
									<li>
										<a href="middleSchool/physics/doorstep/firstPage.jsp">重力</a>
									</li>
									<li>
										<a href="middleSchool/physics/doorstep/firstPage.jsp">浮力</a>
									</li>
									<li>
										<a href="middleSchool/physics/doorstep/firstPage.jsp">摩擦力</a>
									</li>
									<li>
										<a href="middleSchool/physics/doorstep/firstPage.jsp">动量定理</a>
									</li>
									<li>
										<a href="middleSchool/physics/doorstep/firstPage.jsp">动能定理</a>
									</li>

								</ul>
							</fieldset>
						</div>
						<div class="experimentDiv">

							<fieldset>
								<legend>
									领域
								</legend>
								<ul>



									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">测量重力加速度</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">验证动量定理</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">验证机械能守恒</a>
									</li>

								</ul>
							</fieldset>
						</div>


						<div class="mathDiv">
							<fieldset>
								<legend>
									数学知识
								</legend>
							</fieldset>
						</div>
					</div>
				</fieldset>

				<fieldset>
					<legend>
						高中
					</legend>
					<div class="highSchoolDiv">
						<div class="fieldDiv">

							<fieldset>
								<legend>
									领域
								</legend>
								<ul>
									<li>
										<a href="highSchool/physics/mechanics/firstPage.jsp">力学</a>
									</li>
									<li>
										<a href="highSchool/physics/calorifics/firstPage.jsp">热学</a>
									</li>
									<li>
										<a href="highSchool/physics/acoustics/firstPage.jsp">声学</a>
									</li>
									<li>
										<a href="highSchool/physics/optics/firstPage.jsp">光学</a>
									</li>
									<li>
										<a href="highSchool/physics/electromagnetics/firstPage.jsp">电磁学</a>
									</li>
									<li>
										<a href="highSchool/physics/micro/firstPage.jsp">微观世界</a>
									</li>
								</ul>
							</fieldset>
						</div>
						<div class="stepDiv">

							<fieldset>
								<legend>
									进阶课程
								</legend>
								<ul>
									<li>
										<a href="middleSchool/physics/experiment/firstPage.jsp">重力</a>
									</li>
									<li>
										<a href="middleSchool/physics/experiment/firstPage.jsp">浮力</a>
									</li>
									<li>
										<a href="middleSchool/physics/experiment/firstPage.jsp">摩擦力</a>
									</li>
									<li>
										<a href="middleSchool/physics/experiment/firstPage.jsp">动量定理</a>
									</li>
									<li>
										<a href="middleSchool/physics/experiment/firstPage.jsp">动能定理</a>
									</li>
								</ul>
							</fieldset>
						</div>
						<div class="experimentDiv">
							<fieldset>
								<legend>
									实验
								</legend>
								<ul>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">测量重力加速度</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">验证动量定理</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">验证机械能守恒</a>
									</li>
								</ul>
							</fieldset>
						</div>

						<div class="mathDiv">
							<fieldset>
								<legend>
									数学知识
								</legend>
							</fieldset>
						</div>
					</div>
				</fieldset>
				<fieldset>
					<legend>
						大学
					</legend>
					<div class="universeDiv">

						<div class="classicDiv">
							<fieldset>
								<legend>
									经典物理学
								</legend>
								<ul>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">经典力学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">热力学，统计力学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">电动力学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">量子力学</a>
									</li>
								</ul>
							</fieldset>
						</div>
						<div class="branchDiv">
							<fieldset>
								<legend>
									近代物理分支
								</legend>
								<ul>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">凝聚态物理学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">天体物理学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">激光物理学</a>
									</li>
									<li>
										<a href="highSchool/physics/experiment/firstPage.jsp">光电技术</a>
									</li>
								</ul>
							</fieldset>
						</div>
						<div>
							<fieldset>
								<legend>
									前沿物理学
								</legend>
							</fieldset>
						</div>

						<div class="mathDiv">
							<fieldset>
								<legend>
									数学知识
								</legend>
							</fieldset>
						</div>
					</div>
				</fieldset>
			</div>
			<div class="rightDivOfJsp">


				<fieldset>
					<legend>
						经典著作
					</legend>


					<ul>
						<li>
							<a href="classicalBook/firstPage.jsp">数学原理·牛顿</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">光学·牛顿</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">光学·惠更斯</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">两个世界的对话·伽利略</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">广义时空与相对论·爱因斯坦</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">薛定谔的四次演讲</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">时间简史·霍金</a>
						</li>
						<li>
							<a href="classicalBook/firstPage.jsp">费曼物理学讲义</a>
						</li>
						
						 
					</ul>
				</fieldset>
			</div>
		</div>


	</body>
</html>
