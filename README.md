# sample-leaderboard

### 題材
[株式会社ゆめみサーバサイドコーディング試験模試](https://qiita.com/taruhachi/items/56085228fe17537cc0d8)
 - 特に外部に回答になる得るコードを公開しないことなどの注記がなかったのお借りします。

### ルール変更
 - CLIではなくRestAPIやバッチ処理を想定したアプリケーションでOK
 - ファイルは引数ではなく外部ストレージ保存または内部の設定ファイルから読み込み
 - 出力はRestのレスポンスボディでもファイル出力でもOK
 - **要するにDomainに注視して開発してみる！**
 - **DomainのUTもちゃんと書く** (application層からinfra層まで通してのITは略でも可)

### 背景
 - CLIだとどうしてもトランザクションスクリプトで書きがち
 - まずしっかりと仕様の中からDomainを見つけ出し、テストを意識したコードを書けるか練習
 - 若手にもやらせてみたい