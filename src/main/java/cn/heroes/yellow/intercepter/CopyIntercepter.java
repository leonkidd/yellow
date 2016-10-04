package cn.heroes.yellow.intercepter;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.heroes.yellow.entity.TDRow;

/**
 * An helper for coping values between sources and target. Work with NTD*.
 * 
 * @author Leon Kidd
 * @version 1.00, 2016-10-4
 */
public abstract class CopyIntercepter implements NTDIntercepter {

	// -------------- interface --------------------

	/**
	 * Should we begin copy here? And contain current row.
	 * 
	 * @return if true, we will start to copy.
	 */
	@Override
	public abstract boolean begin(TDRow row);

	/**
	 * Should we ignore this row?.
	 * 
	 * @return if true, we will ignore this row.
	 */
	@Override
	public abstract boolean ignore(TDRow row);

	/**
	 * Should we end copy here? And not contain current row.
	 * 
	 * @return if true, we will end until here and this row will be ignored;
	 */
	@Override
	public abstract boolean end(TDRow row);

	/**
	 * Does this sheet have something to copy ?
	 * 
	 * @return true if this sheet should to parse, false if this sheet should be
	 *         ignore.
	 */
	@Override
	public abstract boolean sheet(int index, String name);

	/**
	 * Return the output stream which use to save the copied. You can also
	 * return <tt>NULL</tt>, and deal with this copied value get by
	 * {@link #getCopied()} immediately.
	 * 
	 * @return
	 */
	public abstract OutputStream outputStream();

	// -------------- Getters --------------------

	/**
	 * Get the copied row number of current input source (stream or file). Reset
	 * before every {@link #info(Info)}.
	 * 
	 * @return
	 */
	public int getCurCopyied() {
		return curCopyied;
	}

	/**
	 * Get the copied value of current input source. Reset before every
	 * {@link #info(Info)}.
	 * 
	 * @return
	 */
	@Override
	public List<Object[]> data() {
		return copied;
	}

	// -------------- Implementation --------------------

	/** the copied value of current input source */
	private List<Object[]> copied = new ArrayList<Object[]>();

	private int curCopyied = 0;

	@Override
	public void info(Object info) {
		curCopyied = 0;
		copied.clear();
	}

	@Override
	public void row(TDRow row) {
		Object[] values = row.getValues();
		// add to total copied object
		copied.add(values);
		// current copied ++
		curCopyied++;
	}
}
