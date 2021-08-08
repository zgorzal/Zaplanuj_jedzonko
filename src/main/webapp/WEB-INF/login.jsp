<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="/WEB-INF/header.jsp"/>

<section class="dashboard-section">
    <div class="container pt-4 pb-4">
        <div class="border-dashed view-height">
            <div class="container w-25">
                <form class="padding-small text-center" method="post" action="/login">
                    <h1 class="text-color-darker">Logowanie</h1>
                    <div class="form-group">
                        <input type="email" class="form-control" id="email" name="email"
                               placeholder="podaj adres email">
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" id="password" name="password"
                               placeholder="podaj hasło">
                    </div>
                    <button class="btn btn-color rounded-0" type="submit">Zaloguj</button>
                    <p style="color: crimson">${error}</p>
                </form>
            </div>
        </div>
    </div>
</section>

<jsp:include page="/WEB-INF/footer.jsp"/>