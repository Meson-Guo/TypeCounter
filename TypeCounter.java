package type;

import java.util.HashMap;
import java.util.Map;

public class TypeCounter extends HashMap<Class<?>, Integer> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3532479984050417557L;
	private Class<?> baseType;
	public TypeCounter(Class<?> baseType){
		this.baseType=baseType;
	}
	public void counter(Object obj){
		Class<?> type=obj.getClass();
		if(!baseType.isAssignableFrom(type))
			throw new RuntimeException(obj+"incorrect type:"+type+",should be type or "
					+ "subtype of "+baseType);
		countClass(type);
	}
	private void countClass(Class<?> type) {
		// TODO Auto-generated method stub
		Integer qua=get(type);
		put(type, qua==null?1:qua+1);
		Class<?> superclass=type.getSuperclass();
		if(superclass!=null&&baseType.isAssignableFrom(superclass))
			countClass(superclass);
	}
	public String toString(){
		StringBuilder result=new StringBuilder("{");
		for(Map.Entry<Class<?>, Integer> pair:entrySet()){
			result.append(pair.getKey().getSimpleName());
			result.append("=");
			result.append(pair.getValue());
			result.append(",");
			
		}
		result.delete(result.length()-2, result.length());
		result.append("}");
		return result.toString();
	}

}
