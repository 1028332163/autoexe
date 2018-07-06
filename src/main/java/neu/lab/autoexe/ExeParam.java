package neu.lab.autoexe;

public interface ExeParam {
	public String getMvnCmd();
	public String getParamSig();//store in synchronous file
	public Object getSkipParam();//parameter used to judge whether skip.
}
