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
