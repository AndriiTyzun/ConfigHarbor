package ua.nulp.configharbor.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.nulp.configharbor.model.components.GPU;

@Repository
public interface GPURepository extends JpaRepository<GPU, Long> {
    public GPU findGPUByGpuName(String gpuName);
}
