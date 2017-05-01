1、/addOrderServlet 下单
	recvName
	email
	mphone
	phone
	address
	postcode
	

2、BuyGoodsServlet 添加购物车

3、/detailSearch 高级搜索
	pager.offset
	superType
	subT
	searchMethod
	content

4、/getAllGoodsNameServlet 返回所有商品名称

5、/getAllGoodsServlet 返回所有商品

6、/getAllInformServlet 返回所有info信息

7、/getAllNotesServlet 返回所有的留言

8、/getAllNotesServlet 返回所有的订单

9、GetGoodsByGoodsNameServlet 通过商品名称搜索
	pageSize 每页容量
	GoodsName 商品名称
	
10、GetGoodsByTypeServlet 根据查询类型返回商品
	type 查询类型
	
11、GetGoodsPagerServlet 分页查询商品
	pager.offset 起始数量
	pageSize 每页容量
	
12、GetInformPagerServlet 分页查询新闻
	pager.offset 起始数量
	pageSize 每页容量

13、/getSubTypeBySuperTypeId  通过分类类型查
	superTypeId

14、LoginServlet 登录
	name 用户名
	password 密码
	
15、/updateUserTwo  更新用户信息
	name
	email
	trueName
	sex
	birthday
	address
	address
	postcode
	phone
	mphone
	question
	answer
	tuid

16、/checkNameExist  检查接口名是否存在
	name  用户名

17、/updataPwd  修改密码
	password  新密码

18、/findUser  根据用户名查找用户
name 用户名	

19、/newPassword  忘记密码
name  用户名
password  旧密码
newPassword1 新密码
newPassword2 新密码确认

20、订单list
	/selectOrder

21、订单项list
	/son
	id 订单id

22、留言page
	/pageNoteServlet
	pager.offset 分页偏移量

23、添加留言
	/addNoteServlet
		title
		content
		author
		pic


24、根据大类查书
	/page
		pageSize
		type
		sid

订单购买记录
/showGoodsByIdServlet
		GoodsId










