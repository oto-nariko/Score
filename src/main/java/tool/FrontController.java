package tool;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * フロントコントローラ (Front Controller)
 * すべてのリクエストを一元管理し、適切なアクションクラスを実行する
 */

//.actionで終わるURLはこれを経由する
@WebServlet(urlPatterns={"*.action"})
public class FrontController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("Frontcontroller!");
		PrintWriter out=resp.getWriter();
        try {
            // ① リクエストされたURLのパスを取得
            String path = req.getServletPath().substring(1);
            // 例: "/chapter23/Search.action" → "chapter23/Search.action"

            // ② パスをアクションクラス名の形式に変換
            String name = path.replace(".a", "A").replace('/', '.');
            // 例: "chapter23/Search.action" → "chapter23.SearchAction"

            System.out.println("アクションクラス名 : " + name);
            // ③ アクションクラスのインスタンスを動的に生成
            Action action = (Action)Class.forName(name).
                getDeclaredConstructor().newInstance();
            // クラスを動的ロードし、コンストラクタを呼び出してインスタンスを作成

            // ④ executeメソッドを実行し、フォワード先のURLを取得
            action.execute(req, resp);
            // 例: "/searchResult.jsp" など

        
        } catch(Exception e) {
        	e.printStackTrace(out);
        }
	}

}
