design:
- [ ] storage map: concurrent hashMap 


features implemented: 
- [x] store simple pairs with key and value
- [x] delete pair after specific time
- [x] customized pair timeout
- [x] exposed web api in web controller


思路:
  
- [x] 存储 key, value
- [x] 缓存过期
    - [x] 指定过期时间
- [ ] 大存储100万 - 1亿
   - 一百万个线程如何管理
- [ ] 考虑并发
- [ ] 考虑时间复杂度, 空间复杂度
