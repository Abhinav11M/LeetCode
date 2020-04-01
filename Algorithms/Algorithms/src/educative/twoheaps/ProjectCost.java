package educative.twoheaps;

public class ProjectCost {
	public ProjectCost(int capital, int profit) {
		this.projectCapital = capital;
		this.projectProfit = profit;
	}

	@Override
	public String toString() {
		return "ProjectCost [projectCapital=" + projectCapital + ", projectProfit=" + projectProfit + "]";
	}
	
	public int projectCapital;
	public int projectProfit;
}
