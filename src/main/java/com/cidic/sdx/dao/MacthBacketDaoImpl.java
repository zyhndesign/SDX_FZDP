package com.cidic.sdx.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.cidic.sdx.model.HPListModel;
import com.cidic.sdx.model.HPModel;
import com.cidic.sdx.util.RedisVariableUtil;

@Repository
@Component
@Qualifier(value = "macthBacketDaoImpl")
public class MacthBacketDaoImpl implements MacthBacketDao {

	@Autowired
	@Qualifier(value = "redisTemplate")
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public void addMatchBacket(int userId, HPModel hpModel) {
		
		
		redisTemplate.execute(new RedisCallback<Void>() {
			@Override
			public Void doInRedis(RedisConnection connection) throws DataAccessException {

				String hp_key = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId + RedisVariableUtil.DIVISION_CHAR + hpModel.getHp_num();
				String hp_data = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId;
				
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				
				connection.multi();
				connection.lPush(ser.serialize(hp_data), ser.serialize(hpModel.getHp_num()));
				byte[] hKey = ser.serialize(hp_key);
				
				connection.hSet(hKey,ser.serialize("hp_num"), ser.serialize(hpModel.getHp_num()));
				connection.hSet(hKey,ser.serialize("brand"), ser.serialize(hpModel.getBrand()));
				connection.hSet(hKey,ser.serialize("category"), ser.serialize(hpModel.getCategory()));
				connection.hSet(hKey,ser.serialize("size"), ser.serialize(hpModel.getSize()));
				connection.hSet(hKey,ser.serialize("color"), ser.serialize(hpModel.getColor()));
				connection.hSet(hKey,ser.serialize("price"), ser.serialize(hpModel.getPrice()));
				connection.hSet(hKey,ser.serialize("hpName"), ser.serialize(hpModel.getHpName()));
				
				
				if (hpModel.getImageUrl1() != null && !hpModel.getImageUrl1().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl1"), ser.serialize(hpModel.getImageUrl1()));
				}
				
				if (hpModel.getImageUrl2() != null  && !hpModel.getImageUrl2().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl2"), ser.serialize(hpModel.getImageUrl2()));
				}

				if (hpModel.getImageUrl3() != null  && !hpModel.getImageUrl3().equals("")){
					connection.hSet(hKey,ser.serialize("imageUrl3"), ser.serialize(hpModel.getImageUrl3()));
				}
				
				List<Object> resultList = connection.exec();
				
				return null;
				
			}
		});
	}

	@Override
	public void deleteMatchBacketByUser(int userId, String hpbh) {
		redisTemplate.execute(new RedisCallback<Object>() {
			@Override
			public Object doInRedis(RedisConnection connection) throws DataAccessException {
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				String hp_key = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId + RedisVariableUtil.DIVISION_CHAR + hpbh;
				String hp_data = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId;
				connection.del(ser.serialize(hp_key));
				connection.lRem(ser.serialize(hp_data), 0, ser.serialize(hpbh));
				return null;
			}
		});
	}

	@Override
	public HPListModel getMatchBacketByUser(int userId, int page, int limit) {
		
		redisTemplate.execute(new RedisCallback<HPListModel>() {
			@Override
			public HPListModel doInRedis(RedisConnection connection) throws DataAccessException {
				HPListModel hPListModel = new HPListModel();
				List<HPModel> list = new ArrayList<>();
				RedisSerializer<String> ser = redisTemplate.getStringSerializer();
				String hp_key = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId + RedisVariableUtil.DIVISION_CHAR;
				String hp_data = RedisVariableUtil.MATCH__BACKET_PREFIX + RedisVariableUtil.DIVISION_CHAR + userId;
				List<byte[]> id_list = null;
				id_list = connection.lRange(ser.serialize(hp_data), (page - 1) * limit, page * limit);
				hPListModel.setCount(connection.lLen(ser.serialize(hp_data)));
				
				HPModel hpModel = null;
				for (byte[] id : id_list){
					Map<byte[],byte[]> map = connection.hGetAll(ser.serialize(hp_key + ser.deserialize(id)));
					hpModel = new HPModel();
					Map<String, String> resultMap = new HashMap<>();
					map.forEach((k, v) -> {
						resultMap.put(ser.deserialize(k), ser.deserialize(v));
					});
					hpModel.setId(Integer.parseInt(ser.deserialize(id)));
					hpModel.setHp_num(resultMap.get("hp_num"));
					hpModel.setBrand(resultMap.get("brand"));
					hpModel.setCategory(resultMap.get("category"));
					hpModel.setSize(String.valueOf(resultMap.get("size").charAt(0)));
					hpModel.setColor(resultMap.get("color"));
					hpModel.setPrice(resultMap.get("price"));
					hpModel.setImageUrl1(resultMap.get("imageUrl1"));
					hpModel.setImageUrl2(resultMap.get("imageUrl2"));
					hpModel.setImageUrl3(resultMap.get("imageUrl3"));
					hpModel.setCreateTime(resultMap.get("hpName"));
					
					list.add(hpModel);
				}
				hPListModel.setList(list);
				return hPListModel;
			}
		});
		return null;
	}

}
