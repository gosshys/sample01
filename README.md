# sample01

# 環境変数
## ポート番号
組み込みJettyサーバーの起動用のポート番号を指定する。
例えば5000など。

## postgresqlの接続設定
postgres://[username]:[password]@[host]:[port]/[database]
### 例：
postgres://testuser:password@localhost:5432/testdb

# 起動方法
IDEからならMain.javaを右クリックでRunで動くはず。(Run Configurationなどで環境変数を指定してください)

コマンドラインからだと、mavenからjarをexportして、java -jar xxxxx.jarで起動します。（環境変数は設定してください）

# 稼働確認

## Hello World
ブラウザから

http://localhost:5000

にアクセスして、`Hello from Java!`が表示されることを確認。

*5000は、環境変数で指定するPORT番号です*

## データーベース初期化
ブラウザから

http://localhost:5000/transaction/initdatabase

にアクセスして、`done`が表示されることを確認。

`sensor_m`テーブルと`sensor`テーブルが作成され、初期データが登録されます。

登録処理は以下に書いてあります。

https://github.com/gosshys/sample01/blob/master/src/main/java/dao/TestData.java

## アクション送信処理のサンプル実行
ブラウザから

http://localhost:5000/transaction/sendaction

にアクセスして、`done`が表示されることを確認。

`sensor`テーブルのstatusが0のレコードを拾って、statusを1に変更します。

以下のクラスで実行しています。

https://github.com/gosshys/sample01/blob/master/src/main/java/transaction/SendActionTransaction.java
