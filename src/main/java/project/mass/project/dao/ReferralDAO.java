package project.mass.project.dao;

import project.mass.project.entity.Referral;

import java.util.List;

public interface ReferralDAO {
    void saveReferral(Referral ref);
    void deleteReferral(int referralId);
    void updateReferral(Referral ref);
    Referral getReferral(int id);
    List<Referral> getReferrals();
}
