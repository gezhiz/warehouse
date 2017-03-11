<#--<p class="browserupgrade">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>-->
<![endif]-->
<nav class="site-navbar navbar navbar-default navbar-fixed-top navbar-mega navbar-inverse"
     role="navigation" style="z-index: 1100;">
    <div class="navbar-header">
        <button type="button" class="navbar-toggle hamburger hamburger-close navbar-toggle-left hided"
                data-toggle="menubar">
            <span class="sr-only">Toggle navigation</span>
            <span class="hamburger-bar"></span>
        </button>
        <button type="button" class="navbar-toggle collapsed" data-target="#site-navbar-collapse"
                data-toggle="collapse">
            <i class="icon wb-more-horizontal" aria-hidden="true"></i>
        </button>
        <a class="navbar-brand navbar-brand-center" href="${request.contextPath}/platform/index">
            <img class="navbar-brand-logo navbar-brand-logo-normal" src="${request.contextPath}/static/warehouse/assets/images/logo.png"
                 title="Sdeals">
            <img class="navbar-brand-logo navbar-brand-logo-special" src="${request.contextPath}/static/warehouse/assets/images/logo-blue.png"
                 title="Sdeals">
            <span class="navbar-brand-text hidden-xs">${Session.session_login_user.user}</span>
        </a>
        <button type="button" class="navbar-toggle collapsed" data-target="#site-navbar-search"
                data-toggle="collapse">
            <span class="sr-only">Toggle Search</span>
            <i class="icon wb-search" aria-hidden="true"></i>
        </button>
    </div>
    <div class="navbar-container container-fluid">
        <!-- Navbar Collapse -->
        <div class="collapse navbar-collapse navbar-collapse-toolbar" id="site-navbar-collapse">
            <!-- Navbar Toolbar -->
            <ul class="nav navbar-toolbar">
                <li class="hidden-xs" id="toggleFullscreen">
                    <a class="icon icon-fullscreen" data-toggle="fullscreen" href="#" role="button">
                        <span class="sr-only">Toggle fullscreen</span>
                    </a>
                </li>
            </ul>
            <!-- End Navbar Toolbar -->
            <!-- Navbar Toolbar Right -->
            <ul class="nav navbar-toolbar navbar-right navbar-toolbar-right">
                <li class="dropdown">
                    <a href="${request.contextPath}/platform/logout">退出</a>
                </li>
            </ul>
            <!-- End Navbar Toolbar Right -->
        </div>
        <!-- End Navbar Collapse -->
        <!-- Site Navbar Seach -->
        <div class="collapse navbar-search-overlap" id="site-navbar-search">
            <form role="search">
                <div class="form-group">
                    <div class="input-search">
                        <i class="input-search-icon wb-search" aria-hidden="true"></i>
                        <input type="text" class="form-control" name="site-search" placeholder="Search...">
                        <button type="button" class="input-search-close icon wb-close" data-target="#site-navbar-search"
                                data-toggle="collapse" aria-label="Close"></button>
                    </div>
                </div>
            </form>
        </div>
        <!-- End Site Navbar Seach -->
    </div>
</nav>
<div class="site-menubar site-menubar-light">
    <div class="site-menubar-body">
        <div>
            <div>
                <ul class="site-menu">
                    <li class="site-menu-item has-sub <#if menu=='merchant'>active</#if>">
                        <a href="${request.contextPath}/platform/merchant/merchantList">商户管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='customer'>active</#if>">
                        <a href="${request.contextPath}/platform/customer/customerList">用户管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='withdraw'>active</#if>">
                        <a href="${request.contextPath}/platform/meWithdraw/meWithdrawList">提现管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='order'>active</#if>">
                        <a href="${request.contextPath}/platform/order/orderList">交易管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='appVersion'>active</#if>">
                        <a href="${request.contextPath}/platform/appVersion/appVersionList">app管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='user'>active</#if>">
                        <a href="${request.contextPath}/platform/user/userList">系统管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='personal'>active</#if>">
                        <a href="${request.contextPath}/platform/personal/changePasswd">个人设置</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='biz'>active</#if>">
                        <a href="${request.contextPath}/platform/biz/banner/bannerList">营销运营</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>