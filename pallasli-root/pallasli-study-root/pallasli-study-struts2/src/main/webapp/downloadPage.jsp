<%@ taglib prefix="s" uri="/struts-tags" %>
<html>

<body>
<h1>Struts 2 download file example</h1>

<s:url id="fileDownload" namespace="/" action="download" ></s:url>

<div><div class="ads-in-post hide_if_width_less_800">
<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
<!-- 728x90 - After2ndH4 -->
<ins class="adsbygoogle hide_if_width_less_800" 
     style="display:inline-block;width:728px;height:90px"
     data-ad-client="ca-pub-2836379775501347"
     data-ad-slot="3642936086"
	 data-ad-region="yiibairegion"></ins>
<script>
(adsbygoogle = window.adsbygoogle || []).push({});
</script>
</div></div><h2>Download file - <s:a href="%{fileDownload}">fileABC.txt</s:a>
</h2>
	
</body>
</html>