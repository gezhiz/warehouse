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
            <#--仓储管理系统-->
            <img class="navbar-brand-logo navbar-brand-logo-normal" src="${request.contextPath}/statics/warehouse/assets/images/logo.png"
                 title="WORTHTO">
            <img class="navbar-brand-logo navbar-brand-logo-special" src="${request.contextPath}/statics/warehouse/assets/images/logo.png"
                 title="WORTHTO">
            <span class="navbar-brand-text hidden-xs">${Session.session_user.username}</span>
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
                    <a href="${request.contextPath}/sysops/do_logout">退出</a>
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
                    <li class="site-menu-item has-sub <#if menu=='item'>active</#if>">
                        <a href="${request.contextPath}/sysops/item/itemList">商品管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='itemCategory'>active</#if>">
                        <a href="${request.contextPath}/sysops/category/itemCategoryList">商品类别管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='itemColor'>active</#if>">
                        <a href="${request.contextPath}/sysops/color/itemColorList">商品颜色管理</a>
                    </li>
                    <li class="site-menu-item has-sub <#if menu=='itemSize'>active</#if>">
                        <a href="${request.contextPath}/sysops/size/itemSizeList">商品尺码管理</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>