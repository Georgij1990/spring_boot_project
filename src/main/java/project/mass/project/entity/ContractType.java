package project.mass.project.entity;

public enum ContractType {

    B2B("B2B"),
    TEMPORARY_CONTRACT("Temporary Contract"),
    PERMANENT_CONTRACT("Permanent Contract");

    private String contractType;

    ContractType(String contractType) {
        this.contractType = contractType;
    }

    public String getContractType() {
        return this.contractType;
    }
}
