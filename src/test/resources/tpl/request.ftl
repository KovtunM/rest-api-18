<html>
<#-- @ftlvariable name="data" type="io.qameta.allure.attachment.http.HttpRequestAttachment" -->
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <!-- jQuery -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.4/jquery.min.js" crossorigin="anonymous"></script>

    <!-- Bootstrap -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet" crossorigin="anonymous">
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js" crossorigin="anonymous"></script>

    <!-- Highlight.js -->
    <link type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/styles/github.min.css" rel="stylesheet" />
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/highlight.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/languages/bash.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/languages/json.min.js" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/highlight.js/11.8.0/languages/xml.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript">hljs.highlightAll();</script>

    <style>
        pre {
            white-space: pre-wrap;
        }
    </style>
</head>
<body>
<div>
    <pre><code><#if data.method??>${data.method}<#else>GET</#if>: <#if data.url??>${data.url}<#else>Unknown</#if></code></pre>
</div>

<#if data.body??>
<h4>Body</h4>
<div>
    <pre><code>${data.body}</code></pre>
</div>
</#if>

<#if (data.headers)?has_content>
<h4>Headers</h4>
<div>
    <#list data.headers as name, value>
        <div>
            <pre><code><b>${name}</b>: ${value}</code></pre>
        </div>
    </#list>
</div>
</#if>


<#if (data.cookies)?has_content>
<h4>Cookies</h4>
<div>
    <#list data.cookies as name, value>
        <div>
            <pre><code><b>${name}</b>: ${value}</code></pre>
        </div>
    </#list>
</div>
</#if>

<#if data.curl??>
<h4>Curl</h4>
<div>
    <pre><code>${data.curl}</code></pre
</div>
</#if>
</body>
</html>