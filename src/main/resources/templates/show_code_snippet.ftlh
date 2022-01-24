<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
          href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<span id="load_date">${codeSnippet.date}</span><br/>
<#if (codeSnippet.time >= 0)>
<span id="time_restriction">This code will be available <u>${codeSnippet.time}</u> seconds</span><br/>
</#if>
<#if (codeSnippet.views >= 0)>
<span id="views_restriction"><u>${codeSnippet.views}</u> more views allowed</span><br/>
</#if>
<pre id="code_snippet"><code>${codeSnippet.code}</code></pre>
</body>
</html>