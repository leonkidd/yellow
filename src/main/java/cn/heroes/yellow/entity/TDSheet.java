package cn.heroes.yellow.entity;

/**
 * N Two-Dimensional Table's sheet object<br/>
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 * @since 1.0
 */
public interface TDSheet {

	/**
	 * Get the name of sheet.
	 * 
	 * @return
	 */
	String getName();

	/**
	 * Get the index of sheet.
	 * 
	 * @return index of the sheet (1 based)
	 */
	int getIndex();

	/**
	 * Get the raw sheet of implements
	 * 
	 * @return
	 */
	Object getRaw();

}
