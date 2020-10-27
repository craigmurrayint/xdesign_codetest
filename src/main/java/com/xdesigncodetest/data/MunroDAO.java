package com.xdesigncodetest.data;

import com.xdesigncodetest.model.Munro;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MunroDAO {

    List<Munro> getMunros();
}
