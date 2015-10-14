package com.pallas.design.service;

import java.util.List;

import com.google.gson.JsonObject;
import com.pallas.design.bean.Comp;
import com.pallas.design.bean.CompConfig;
import com.pallas.design.bean.CompType;

public interface CompService {
	public JsonObject getCompFromConfig();

	public List<Comp> loadCompsByType(CompType compType);

	public List<Comp> loadComps();

	public List<CompType> loadCompTypes();

	public List<CompConfig> loadCompConfigs();

	public CompType saveCompType(CompType compType);

	public Comp saveComp(Comp comp);

	public CompConfig saveCompConfig(CompConfig compConfig);

}
