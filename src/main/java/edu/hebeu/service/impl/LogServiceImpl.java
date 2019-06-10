package edu.hebeu.service.impl;


import java.util.List;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;

import edu.hebeu.entity.Log;
import edu.hebeu.mapper.LogMapper;
import edu.hebeu.service.LogService;

public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements LogService{

	@Override
	public List<Log> selectLogAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Log selectLogById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insertLog(Log log) {
		// TODO Auto-generated method stub
		return 0;
	}

}
