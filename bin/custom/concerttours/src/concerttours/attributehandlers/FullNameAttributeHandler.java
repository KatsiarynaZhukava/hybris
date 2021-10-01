package concerttours.attributehandlers;

import concerttours.model.BandMemberModel;
import de.hybris.platform.servicelayer.model.attribute.AbstractDynamicAttributeHandler;
import org.springframework.stereotype.Component;

@Component
public class FullNameAttributeHandler extends AbstractDynamicAttributeHandler<String, BandMemberModel>
{
    @Override
    public String get(final BandMemberModel bandMember)
    {
        if (bandMember == null)
        {
            throw new IllegalArgumentException("Band member should not be null");
        }
        return bandMember.getFirstName() + " " + bandMember.getLastName();
    }

	@Override
	public void set(final BandMemberModel bandMember, final String value)
	{
		if (bandMember != null && value != null)
		{
			String[] split = value.split(" ");
            bandMember.setFirstName(split[0]);
            bandMember.setLastName(split[1]);
		}
	}
}