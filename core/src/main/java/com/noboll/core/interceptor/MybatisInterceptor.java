package com.noboll.core.interceptor;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.core.annotation.AnnotationUtils;

import com.noboll.core.annotation.CreatedBy;
import com.noboll.core.annotation.CreatedDate;
import com.noboll.core.annotation.LastModifiedBy;
import com.noboll.core.annotation.LastModifiedDate;
import com.noboll.core.base.entity.BaseEntity;
import com.noboll.core.context.BaseContext;
import com.noboll.core.util.PropertiesUtil;
import com.noboll.core.util.ReflectUtil;
import com.noboll.core.util.StringUtil;

@Intercepts({@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}) })
public class MybatisInterceptor  implements Interceptor {
	private BaseContext<BaseEntity> context;
	
	@Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
        Object parameter = invocation.getArgs()[1];
        List<Field> fields =  ReflectUtil.getClassFields(parameter.getClass(), true);
        Date currentDate = new Date();
        String userId=PropertiesUtil.getSettingValue("sys.user.admin.id");
        if(null!=BaseContext.getLoginUser()) {
        	userId=BaseContext.getLoginUser().getId();
        }
        if(SqlCommandType.UPDATE==sqlCommandType) {
            for (Field field : fields) {
                if (AnnotationUtils.getAnnotation(field, LastModifiedBy.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter,userId);
                    field.setAccessible(false);
                }
                if (AnnotationUtils.getAnnotation(field, LastModifiedDate.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter,currentDate);
                    field.setAccessible(false);
                }
            }
        } else if(SqlCommandType.INSERT==sqlCommandType){
            for (Field field : fields) {
                if (AnnotationUtils.getAnnotation(field, CreatedBy.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter,userId);
                    field.setAccessible(false);
                }
                if (AnnotationUtils.getAnnotation(field, CreatedDate.class) != null) {
                    field.setAccessible(true);
                    field.set(parameter,currentDate);
                    field.setAccessible(false);
                }
            }
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {

    }

	public void setContext(BaseContext<BaseEntity> context) {
		this.context = context;
	}
}
